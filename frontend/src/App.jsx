import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./layouts/header/Header.jsx";
import Footer from "./layouts/footer/Footer.jsx";
import Home from "./pages/home/Home.jsx";
import About from "./pages/about/About.jsx";
import NotFound from "./pages/error/notFound/404.jsx";
import Course from "./pages/course/Course.jsx";
import Profile from "./pages/profile/Profile.jsx";
import Register from "./pages/register/Register.jsx";
import Login from "./pages/login/Login.jsx";
import CoursesPage from "./pages/courses/CoursesPage.jsx";
import CartPage from "./pages/cart/CartPage.jsx";
import "./index.css";
import { CurrencyProvider } from "./components/currencySelector/CurrencyContext.jsx";
import CartProvider from "./pages/cart/CartProvider.jsx";
import AdminPage from "./pages/admin/Admin.jsx";
import { AuthProvider } from "./pages/admin/AuthProvider.jsx";
import PurchasedPage from "./pages/purchased/PurchasedPage.jsx";
import PostCourse from "./components/crudTest/post/course/PostCourse.jsx";
import AdminCoursePage from "./pages/admin/AdminCourse.jsx";
import DeleteCourse from "./components/crudTest/delete/course/DeleteCourse.jsx";
import UpdateCourse from "./components/crudTest/update/course/PutCourse.jsx";
import AdminUserPage from "./pages/admin/AdminUser.jsx";
import PostUser from "./components/crudTest/post/user/PostUser.jsx";
import DeleteUser from "./components/crudTest/delete/user/DeleteUser.jsx";
import UpdateUser from "./components/crudTest/update/user/PutUser.jsx";

export default function App() {
  const [targetCurrency, setTargetCurrency] = useState("NOK");

  return (
    <AuthProvider>
        <CurrencyProvider>
          <CartProvider>
            <Router>
              <Header
                targetCurrency={targetCurrency}
                setTargetCurrency={setTargetCurrency}
              />
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/courses" element={<CoursesPage />} />
                <Route path="/course/:id" element={<Course />} />
                <Route path="*" element={<NotFound />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/cart" element={<CartPage />} />
                <Route path="/admin" element={<AdminPage />} />
                <Route path="/purchased" element={<PurchasedPage />} />
                <Route path="/admin/course" element={<AdminCoursePage />} />
                <Route
                  path="/admin/course/newCourse"
                  element={<PostCourse />}
                />
                <Route
                  path="/admin/course/deleteCourse/:id"
                  element={<DeleteCourse />}
                />
                <Route
                  path="/admin/course/updateCourse/:id"
                  element={<UpdateCourse />}
                />
                <Route path="/admin/user" element={<AdminUserPage />} />
                <Route path="/admin/user/newUser" element={<PostUser />} />
                <Route
                  path="/admin/user/deleteUser/:id"
                  element={<DeleteUser />}
                />
                <Route
                  path="/admin/user/updateUser/:id"
                  element={<UpdateUser />}
                />
              </Routes>
              <Footer />
            </Router>
          </CartProvider>
        </CurrencyProvider>
    </AuthProvider>
  );
}
