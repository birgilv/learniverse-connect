import { React, useState } from 'react';
import { getImagesFromServer, uploadImageToServer } from '../../services/image-service';
import { updateUserOnServer } from '../../services/user-request';
import { useNavigate } from 'react-router-dom';

export default function UpdateAvatar({ user }) {
  const [file, setFile] = useState(null);
  const [images, setImages] = useState([]);
  const navigate = useNavigate();

  const handleFileChange = (e) => {
    const chosenImage = e.target.files[0];
    setFile(chosenImage);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();
      formData.append('file', file);
      formData.append('alt', "Profile image for a user");

      await uploadImageToServer(formData);
      const imagesResponse = await getImagesFromServer();
      setImages(imagesResponse.data);

      const imgId = imagesResponse.data.length;
      const userData = {
        id: user.id,
        username: user.username,
        startDate: user.startDate,
        email: user.email,
        password: user.password,
        role: user.roleId,
        imgId: 11
      };

      console.log('NNNN:', userData)

      await updateUserOnServer(user.id, userData);
      alert('User updated successfully');
    } catch (error) {
      console.error("Error uploading file or updating user:", error);
    }
  };

//   return (
//     <div>
//       <form onSubmit={handleSubmit}>
//         <label>
//           Upload File
//           <input type="file" name="file" accept=".png, .jpg, .jpeg, .webp, .svg" onChange={handleFileChange}/>
//         </label><br/>
//         <button type='submit'>Upload</button>
//       </form>
//     </div>
//   );
}
