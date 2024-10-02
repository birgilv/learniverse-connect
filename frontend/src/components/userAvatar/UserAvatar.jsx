import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getUserByEmail } from "../../services/user-request";
import "./UserAvatar.css";
import { generateImageUrl } from "../../services/image-service";

function UserAvatar({ user }) {
  const [userData, setUserData] = useState(null);
  const [loading, setLoading] = useState(true);
  // const [userId, setUserId] = useState(null);
  let avatar = "";

  useEffect(() => {
    async function fetchUser() {
      try {
        const response = await getUserByEmail(user.sub);
        setUserData(response.data);
      } finally {
        setLoading(false); // Ensure loading is set to false regardless of success or failure
      }
    }

    fetchUser();
  }, [user]);

  if (userData) {
    avatar = userData.username.slice(0, 2).toUpperCase();
  }

  return (
    <Link to="/profile" className="user-avatar-link" aria-label="View Profile">
      <div className="user-avatar">
        {!loading && userData && (
          <div role="img" aria-label={`Avatar for ${userData.username}`}>
            {avatar}
          </div>
        )}
      </div>
    </Link>
  );
}

export default UserAvatar;
