//Code generated with the help of ChatGPT

import React, { useEffect, useState, useContext, useRef } from "react";
import { Link, useParams } from "react-router-dom";
import "./Course.css";
import "../../index.css";
import { CartContext } from "../cart/CartProvider";
import { useCurrencyContext } from "../../components/currencySelector/CurrencyContext";
import { getOneCourseFromServer } from "../../services/course-service";
import {addFavoriteCourseToServer,deleteFavoriteCourseOnServer,} from "../../services/favorite-course";
import { getAllProvidersForACourse } from "../../services/course-provider";
import NotFound from "../error/notFound/404";
import GetImage from "../../components/crudTest/post/image/GetImage";

import iconStartDate from "/icons/course/startdate-icon.png";
import iconEndDate from "/icons/course/enddate-icon.png";
import iconHoursPerWeek from "/icons/course/hours-icon.png";
import iconCredits from "/icons/course/credits-icon.png";
import iconExpert from "/icons/course/expert-icon.png";
import iconBeginner from "/icons/course/beginner-icon.png";
import iconIntermediate from "/icons/course/intermediate-icon.png";
import iconCategory from "/icons/course/category-icon.png";
import { AuthContext } from "../admin/AuthProvider";

function Course() {
  const { id } = useParams();
  const [course, setCourse] = useState(null);
  const [providers, setProviders] = useState([]);
  const [selectedProvider, setSelectedProvider] = useState(null);
  const [showWarning, setShowWarning] = useState(false);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [courseAdded, setCourseAdded] = useState(false);
  const [favorited, setFavorited] = useState(false);
  const [expandedDescription, setExpandedDescription] = useState(false);
  const { cart, addToCart } = useContext(CartContext);
  const { targetCurrency } = useCurrencyContext();
  const { user } = useContext(AuthContext); // Get the authentication state
  const addToCartButtonRef = useRef(null); // Ref for the add to cart button

  useEffect(() => {
    const fetchData = async () => {
      try {
        const courseData = await getOneCourseFromServer(id);
        const providerData = await getAllProvidersForACourse(
          id,
          targetCurrency
        );
        setCourse(courseData.data);
        setProviders(providerData.data);

        const favorites = localStorage.getItem("favorites");
        if (favorites) {
          const favoritesArray = JSON.parse(favorites);
          const courseIdString = id.toString();
          const stringFavoritesArray = favoritesArray.map((fav) =>
            fav.toString()
          );
          const isFavorited = stringFavoritesArray.includes(courseIdString);
          setFavorited(isFavorited);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [id, targetCurrency]);

  useEffect(() => {
    const alreadyInCart = cart.some(
      (item) => item.course.course.id === course?.id
    );
    setCourseAdded(alreadyInCart);
  }, [cart, course]);

  useEffect(() => {
  }, [favorited]);

  const handleAddToCart = () => {
    if (!selectedProvider) {
      setShowWarning(true);
      setShowSuccessMessage(false);
    } else if (!courseAdded) {
      addToCart({ course, selectedProvider });
      setShowSuccessMessage(true);
      setCourseAdded(true);
    }
  };

  const handleToggleFavorite = async () => {
    const userId = localStorage.getItem("ActiveUserId");
    try {
      let favorites = localStorage.getItem("favorites");
      if (!favorites) favorites = "[]";
      let favoritesArray = JSON.parse(favorites);

      if (!favorited) {
        await addFavoriteCourseToServer(userId, id);
        favoritesArray.push(id);
        localStorage.setItem("favorites", JSON.stringify(favoritesArray));
        setFavorited(true);
      } else {
        await deleteFavoriteCourseOnServer(userId, id);
        favoritesArray = favoritesArray.filter((favId) => favId !== id);
        localStorage.setItem("favorites", JSON.stringify(favoritesArray));
        setFavorited(false);
      }
    } catch (error) {
      console.error("Failed to update favorites:", error);
    }
  };

  const handleProviderSelection = (provider) => {
    setSelectedProvider(provider);
  };

  const handleKeyPress = (e, provider) => {
    if (e.key === "Enter") {
      handleProviderSelection(provider);
    }
  };

  useEffect(() => {
    if (addToCartButtonRef.current) {
      console.log("Add to Cart button:", addToCartButtonRef.current);
    }
  }, []);

  if (!course || !providers.length) {
    return <NotFound />;
  }

  const difficultyIcons = {
    Beginner: iconBeginner,
    Intermediate: iconIntermediate,
    Expert: iconExpert,
  };

  const difficultyIcon = difficultyIcons[course.level.difficulty] || null;

  return (
    <div className="Course">
      <div className="button-section">
        <Link to={`/courses`}>
          <button className="goBackButton">← Back to Courses</button>
        </Link>
      </div>
      <div className="title-image-favorite">
        <div className="title-image-section">
          <h2 className="title">{course.title}</h2>
          <div className="course-image-section">
            <GetImage imageId={course.id} />
          </div>
        </div>
        <button
          className={`favoriteButton ${favorited ? "favorited" : ""}`}
          onClick={user ? handleToggleFavorite : null}
          onKeyDown={(e) => handleKeyPress(e, selectedProvider)}
          tabIndex={0}
          disabled={!user}
        >
          {favorited ? "★ Remove from Favorites" : "☆ Add to Favorites"}
        </button>
      </div>
      <div className="course-content">
        <div className="description-container">
        <h4>Certification: {course.relatedCertification}</h4>
          <h2>Description</h2>
          <p>
            {expandedDescription
              ? course.description
              : `${course.description.slice(0, 100)}...`}
          </p>
          <button
            className="expand-description-button"
            onClick={() => setExpandedDescription(!expandedDescription)}
          >
            {expandedDescription ? "Read Less" : "Read More"}
          </button>
        </div>
        <div className="attributes-container">
          <div className="start-date-attribute">
            <img src={iconStartDate} alt={'Black hourglass with the sand at the top'} />
            <h3>Start Date:</h3>
            <span>{course.startDate}</span>
          </div>
          <div className="end-date-attribute">
            <img src={iconEndDate} alt={'Black hourglass with the sand at the bottom'} />
            <h3>End Date:</h3>
            <span>{course.endDate}</span>
            </div>
          <div className="hours-attribute">
            <img src={iconHoursPerWeek} alt={"Black clock with the clock-arms pointing at 03:00 o'clock"} />
            <h3>Hours Per Week:</h3>
            <span>{course.hoursPerWeek}</span>
          </div>
          <div className="credits-attribute">
            <img src={iconCredits} alt={'Black medal with a star in the middle'} />
            <h3>Credits:</h3>
            <span>{course.credit}</span>
          </div>
          <div className="level-attribute">
            <img src={difficultyIcon} alt="Bar chart representing the difficulty of the course" />
            <h3>Difficulty:</h3>
            <span>{course.level.difficulty}</span>
          </div>
          <div className="category-attribute">
            <img src={iconCategory} alt="Different shapes representing the various categories the courses can be" />
            <h3>Category:</h3>
            <span>{course.category.subject}</span>
          </div>
        </div>
        <h3>Providers:</h3>
        <div className="providers-container">
  {providers.map((provider) => (
    <div
      key={provider.providerId}
      className={`provider-card ${
        selectedProvider === provider ? "selected" : ""
      } ${(!user || courseAdded) ? "disabled" : ""}`}
      onClick={!user || courseAdded ? null : () => handleProviderSelection(provider)}
    >
      <input
        type="radio"
        name="provider"
        checked={selectedProvider === provider}
        onChange={() => handleProviderSelection(provider)}
        disabled={!user || courseAdded}
      />
      <span className="custom-radio"></span>
      <div className="provider-info">
        <div className="provider-name">{provider.providerName}</div>
        <div className="provider-price">
          Price: {Math.ceil(provider.price)} {provider.currency}
        </div>
      </div>
    </div>
  ))}
        </div>
        <div className="notification-container">
          <div className="notification-placeholder">
            {showWarning && <div className="warning-placeholder"></div>}
            {showSuccessMessage && <div className="success-message-placeholder"></div>}
          </div>
          {showWarning && (
            <div className="warning" role="alert">
              Please select a provider before adding to cart.
            </div>
          )}
          {showSuccessMessage && (
            <div className="success-message" role="alert">
              Course successfully added to cart!
            </div>
          )}
        </div>
        <button
          className="addToCartButton"
          onClick={user ? handleAddToCart : null}
          disabled={!selectedProvider || courseAdded || !user}
          onKeyDown={(e) => handleKeyPress(e, selectedProvider)}
          tabIndex={0}
          ref={addToCartButtonRef}
        >
          {courseAdded ? "Already Added to Cart" : "Add to Cart"}
        </button>
      </div>
    </div>
  );

}

export default Course;