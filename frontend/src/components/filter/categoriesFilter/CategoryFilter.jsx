import React, { useEffect, useState } from 'react';
import { getCategoriesFromServer } from '../../../services/category-service';

export default function CategoryFilter({ onCategoryChange }) {
  const [categories, setCategories] = useState([]);

  const handleCategoryChange = (event) => {
    const categoryName = event.target.value;
    onCategoryChange(categoryName);
  };

  async function fetchCategories() {
    const response = await getCategoriesFromServer();
    setCategories(response.data);
    console.log(categories +  "dadaw")
  }

  useEffect(() => {
    fetchCategories();
  }, []);

  return (

    <div className='category-container'>
      <label htmlFor="categorySelect" aria-labelledby="categorySelectLabel" className='category-label'> Select a category:</label>
      <select id="categorySelect" className="category-select" onChange={handleCategoryChange}>
        <option value="">All Categories</option>
        {categories.map((category) => (
          <option key={category.id} value={category.subject}>{category.subject}</option>
        ))}
      </select>
    </div>
  );
}
