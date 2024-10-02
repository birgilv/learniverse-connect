import React, { useEffect, useState, useContext } from "react";
import "./Profile.css";
import { getUserByEmail } from "../../services/user-request";
import { AuthContext } from "../admin/AuthProvider";
import Unauthorized from "../error/unauthorized/401";
import { Link } from "react-router-dom";
import GetFavoriteCourses from "../../components/crudTest/read/favoriteCourses/GetFavoriteCourses";
import UserAvatar from "../../components/userAvatar/UserAvatar";
import CurrencySelector from "../../components/currencySelector/CurrencySelector";
import UpdateAvatar from "./UpdateAvatar";

export default function Profile() {
  const auth = useContext(AuthContext);
  const [userId, setUserId] = useState(null);
  const [isUser, setIsUser] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [loading, setLoading] = useState(true);
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [startDate, setStartDate] = useState("");
  const [images, setImages] = useState([]);
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    if (auth.user && auth.user.roles && Array.isArray(auth.user.roles)) {
      const isUser = auth.user.roles.some(
        (role) => role.authority === "ROLE_USER"
      );

      const isAdmin = auth.user.roles.some(
        (role) => role.authority === "ROLE_ADMIN"
      );
      setIsUser(isUser);
      setIsAdmin(isAdmin);
    }

    setLoading(false);
  }, [auth.user]);

  useEffect(() => {
    async function getUser() {
      try {
        const user = auth.user;
        if (auth.user != null) {
          const convertedEmail = user.sub.replace("@", "%40");
          const res = await getUserByEmail(convertedEmail);
          const currentUser = res.data;
          setCurrentUser(currentUser); // Store the current user in state
          setUserId(currentUser.id);
          localStorage.setItem("ActiveUserId", currentUser.id);
          setUserName(currentUser.username);
          setEmail(currentUser.email);
          setStartDate(currentUser.startDate);
        }
      } catch (error) {
        console.error("Error fetching user:", error);
      }
    }

    getUser();
  }, [auth.user]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!isUser && !isAdmin) {
    return <Unauthorized />;
  }
  return (
    <div className="profilepage">
      <div className="card">
        <UserAvatar className="userAvatar-profile" user={auth.user} />
        <div className="data">
          <Intro
            userName={userName}
            email={email}
            startDate={startDate}
            currentUser={currentUser} // Pass currentUser to Intro
          />
        </div>
      </div>
      {userId && <GetFavoriteCourses userId={userId} />}
    </div>
  );
}

function Intro({ userName, email, startDate, currentUser }) {
  const auth = useContext(AuthContext);

  const handleSignOff = () => {
    auth.logout();
  };
  return (
    <div className="intro">
      <h1>{userName}</h1>
      <h2>{email}</h2>
      <p>User since: {startDate}</p>
      <div className="buttons">
        <Link to="/">
          <button className="button" onClick={handleSignOff}>
            Sign Off
          </button>
        </Link>
      </div>
      <p>
        Welcome to your profile! Here you may access your favorite courses. Dive
        in whenever you're ready! Happy learning!
      </p>
      <CurrencySelector currencies={["NOK", "USD", "EUR", "GBP"]} />
      <section>
        <UpdateAvatar user={currentUser} /> {/* Pass currentUser to UpdateAvatar */}
      </section>
    </div>
  );
}
