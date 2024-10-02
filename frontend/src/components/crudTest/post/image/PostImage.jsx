import { React, useState } from 'react';
import { uploadImageToServer } from '../../../../services/image-service';

/**
 * 
 *
 * @returns 
 */
export default function PostImage() {
  const [file, setFile] = useState(null);
  const [altText, setAltText] = useState("");
  const [previewImage, setPreviewImage] = useState(null);

  const handleFileChange = (e) => {
    const chosenImage = e.target.files[0];
    setFile(chosenImage);
    setPreviewImage(URL.createObjectURL(chosenImage));
  };

  const handleAltTextChange = (e) => {
    setAltText(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!previewImage || !altText) {
      console.error("Please select a file and enter an image description.")
      return;
    }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('alt', "Profile image for a user");

    try {
      uploadImageToServer(formData);

      setFile(null);
      setAltText("");
      setPreviewImage(null)
    } catch (error) {
      console.error("Error uploading file:", error);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Upload File
          <input type="file" name="file" accept=".png, .jpg, .jpeg, .webp, .svg" onChange={handleFileChange}/>
        </label><br/>
        {/* <label>
          Image Description
          <input value={altText} onChange={handleAltTextChange}/>
        </label><br/>
        <div>
          <img src={previewImage} width={300} alt={previewImage ? 'Image Preview' : null}/>
        </div> */}
        <button type='submit'>Upload</button>
      </form>
    </div>
  )
}