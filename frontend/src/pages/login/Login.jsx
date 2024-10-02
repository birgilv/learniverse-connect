import React, { useState, useContext, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../../index.css";
import "./Login.css";
import { AuthContext } from "../admin/AuthProvider";
import { postAuthToServer } from "../../services/user-request";
import { getUserByEmail } from "../../services/user-request";
import { getFavoriteCoursesFromAUser } from "../../services/favorite-course";

function parseJwt(token) {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
}

function saveUserDataToStorage(userData) {
  try {
    const userDataWithAuth = { ...userData, isAuthenticated: true };
    
    const data = JSON.stringify(userDataWithAuth);

    localStorage.setItem("user", data);
    console.log(data);
  } catch (error) {
    console.error("Could not save user data to localStorage:", error);
  }
}

function Login() {
  const [userId, setUserId] = useState(null);
  const { login } = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginSuccess, setLoginSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (loginSuccess) {
      const timer = setTimeout(() => {
        navigate("/");
      }, 1500);
      return () => clearTimeout(timer);
    }
  }, [loginSuccess, navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password.length < 6) {
      setErrorMessage("Password must be at least 6 characters long.");
      return;
    }
    try {
      const formData = new FormData();
      formData.append("email", email);
      formData.append("password", password);
      const response = await postAuthToServer(formData);

      const userData = parseJwt(response.data.jwt);
      console.log("User data:", userData);
      saveUserDataToStorage(userData);
      localStorage.setItem("token", response.data.jwt);

      login(userData);
      setLoginSuccess(true);

      const convertedEmail = email.replace("@", "%40");
      const res = await getUserByEmail(convertedEmail);
      console.log("User response:", res);
      const currentUser = res.data.id;
      setUserId(currentUser);
      localStorage.setItem("ActiveUserId", userId);

      const favRes = await getFavoriteCoursesFromAUser(currentUser);
      console.log("Favorite courses response:", favRes);
      const resArray = favRes.data;
      console.log("Favorite courses data:", resArray);
      const favArray = resArray.map((item) => item.course.id.toString());
      //const favCourseArray = resArray.map((item) => item.course_id);
      localStorage.setItem("favorites", JSON.stringify(favArray));
      //localStorage.setItem("favoriteCourseId", JSON.stringify(favCourseArray));
    } catch (error) {
      console.error("Login failed:", error);
      setErrorMessage("Login failed. Please try again.");
    }
  };

  useEffect(() => {
    if (userId !== null) {
      localStorage.setItem("ActiveUserId", userId);
    }
  }, [userId]);

  return (
    <div>
      <h1>Login</h1>

      <form onSubmit={handleSubmit} aria-labelledby="loginFormTitle">
        <div className="imgcontainer">
          <img src="/login/login.png" alt="Standard profile picture of a person in a circle" className="avatar" />
        </div>
        <fieldset className="form-container">
          <legend id="loginFormTitle">Login Information</legend>
          <div className="input-group">
            <label htmlFor="email">Email</label>
            <input
              type="text"
              id="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              aria-required="true"
            />
          </div>

          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              aria-required="true"
            />
          </div>

          <button type="submit">Login</button>
        </fieldset>
        {errorMessage && (
          <p className="error-message" role="alert">
            {errorMessage}
          </p>
        )}
        <p>
          No account? Sign up{" "}
          <Link to="/register" className="register-link">
            here
          </Link>
          !
        </p>
      </form>
      {loginSuccess && (
        <div className="success-message" aria-live="polite">
          Login Successful!
        </div>
      )}
    </div>
  );
}

export default Login;
