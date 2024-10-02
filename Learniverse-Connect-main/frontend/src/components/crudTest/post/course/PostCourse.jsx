import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Select from 'react-select';
import "./PostCourse.css";
import { useNavigate, Link } from "react-router-dom";
import { addCourseToServer } from '../../../../services/course-service';
import { getLevelsFromServer } from '../../../../services/levels-service';
import { getCategoriesFromServer } from '../../../../services/category-service';
import { getImagesFromServer } from '../../../../services/image-service';
//import { getProvidersFromServer } from '../../../../services/provider-service';
//import { getTagsFromServer } from '../../../../services/tags-service';

export default function PostCourse() {
  const [formData, setFormData] = useState({
    id: '',
    title: '',
    levelId: '',
    categoryId: '',
    startDate: '',
    endDate: '',
    credit: '',
    hoursPerWeek: '',
    relatedCertification: '',
    description: '',
    imageId: '',
    hidden: ''
    //providers: [],
    //tags: [] 
  });

  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);
  const [levels, setLevels] = useState([]);
  const [images, setImages] = useState([]);
  //const [providers, setProviders] = useState([]);
  //const [tags, setTags] = useState([]);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const value = e.target.value;
    setFormData({
      ...formData,
      [e.target.id]: value
    });
  };

  /*
  const handleMultiSelectChange = (selectedOptions, field) => {
    setFormData(prevData => ({
      ...prevData,
      [field]: selectedOptions.map(option => option.value)
    }));
  };
  */

  useEffect(() => {
    const fetchCourseData = async () => {
      try {
        const categoriesResponse = await getCategoriesFromServer();
        setCategories(categoriesResponse.data);
        const levelsResponse = await getLevelsFromServer();
        setLevels(levelsResponse.data);
        const imagesResponse = await getImagesFromServer();
        setImages(imagesResponse.data);
        /*
        const providerResponse = await getProvidersFromServer();
        setProviders(providerResponse.data);
        setLevels(levelsResponse.data);
        const tagResponse = await getTagsFromServer();
        setTags(tagResponse.data);
        */

      } catch (error) {
        console.error('Error fetching course data:', error);
      }
    };
    fetchCourseData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const userData = {
        id: formData.id,
        title: formData.title,
        levelId: parseInt(formData.levelId),
        categoryId: parseInt(formData.categoryId),
        startDate: formData.startDate,
        endDate: formData.endDate,
        credit: parseFloat(formData.credit),
        hoursPerWeek: parseFloat(formData.hoursPerWeek),
        relatedCertification: formData.relatedCertification,
        description: formData.description,
        imageId: parseInt(formData.imageId),
        hidden: parseInt(formData.hidden),
      };

      
      await addCourseToServer(userData);
      navigate('/admin/course');
      alert('Course added successfully');

    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred while submitting the form. Please try again.');
    };
  };

  /*
  const providerOptions = providers.map(provider => ({ value: provider.id, label: provider.name }));
  const tagOptions = tags.map(tag => ({ value: tag.id, label: tag.tag }));
  */

  return (
    <div className='form'>
      <div>
        <Link to={"/admin/course"}>
          <button className='go-back-button'>← Go back</button>
        </Link>
            
      </div>
      <h1>Create new course</h1>
      <p>Here you can create a course</p>

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor='title'>Title</label>
          <input id='title' placeholder="Enter course title" value={formData.title} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor="levelId">Level</label>
          <select id="levelId" value={formData.levelId} onChange={handleChange} required>
            <option value="" disabled>Select level</option>
            {levels.map((level) => (
              <option key={level.id} value={level.id}>{level.difficulty}</option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="categoryId">Category</label>
          <select id="categoryId" value={formData.categoryId} onChange={handleChange} required>
            <option value="" disabled>Select category</option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>{category.subject}</option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label htmlFor='startDate'>Start date</label>
          <input id='startDate' type='date' value={formData.startDate} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='endDate'>End date</label>
          <input id='endDate' type='date' value={formData.endDate} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='credit'>Credit</label>
          <input id='credit' type='number' placeholder="Enter credits" value={formData.credit} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='hoursPerWeek'>Hours per week</label>
          <input id='hoursPerWeek' type='number' placeholder="Enter hours per week" value={formData.hoursPerWeek} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='relatedCertification'>Related certification</label>
          <input id='relatedCertification' placeholder="Enter related certification" value={formData.relatedCertification} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label htmlFor='description'>Description</label>
          <textarea id='description' placeholder="Enter course description" value={formData.description} onChange={handleChange} required />
        </div>

        {/*
        <div className="form-group">
          <label htmlFor="providers">Providers</label>
          <Select
            isMulti
            id="providers"
            options={providerOptions}
            onChange={(selectedOptions) => handleMultiSelectChange(selectedOptions, 'providers')}
            value={providerOptions.filter(option => data.providers.includes(option.value))}
          />
        </div>

        <div className="form-group">
          <label htmlFor="tags">Tags</label>
          <Select
            isMulti
            id="tags"
            options={tagOptions}
            onChange={(selectedOptions) => handleMultiSelectChange(selectedOptions, 'tags')}
            value={tagOptions.filter(option => data.tags.includes(option.value))}
          />
        </div>
        */}

        <div className="form-group">
          <label htmlFor="imageId">Image Id</label>
          <select id="imageId" value={formData.imageId} onChange={handleChange} required>
            <option value="" disabled>Select image</option>
            {images.map((image) => (
              <option key={image.id} value={image.id}>{image.fileName}</option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="hidden">Hidden</label>
          <select id="hidden" value={formData.hidden} onChange={handleChange} required>
            <option value={1}>True</option>
            <option value={0}>False</option>
          </select>
        </div>
        
        <button type='submit'>Post</button>
      </form>
    </div>
  );
}