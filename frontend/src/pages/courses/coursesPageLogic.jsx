//class generated with help from CHATGPT

import { useState, useEffect } from 'react';
import CourseDataCombiner from '../../components/combiner/CourseDataCombiner.jsx';
import { useCurrencyContext } from '../../components/currencySelector/CurrencyContext.jsx';
import { filterLogic } from "./FilterLogic.jsx";
import { getCoursesFromServer } from '../../services/course-service.jsx';
import { getCategoriesFromServer } from '../../services/category-service.jsx';
import { getCheapestPriceForEachCourse, getMostExpensivePriceForEachCourse } from '../../services/course-provider.jsx';
import { getTagsFromServer} from '../../services/tags-service.jsx'; // Import the new services
import { getCourseTagsFromServer} from '../../services/course-tags-service.jsx'; // Import the new services

function useCoursesPageLogic() {
  const { targetCurrency } = useCurrencyContext();
  const [combinedCourses, setCombinedCourses] = useState([]);
  const [filters, setFilters] = useState({
    searchQuery: '',
    minPrice: 0,
    maxPrice: 100000, // Initialize with a default value
    sortBy: '',
    sortOrder: 'asc',
    category: ''
  });

  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const coursesData = await getCoursesFromServer();
        const cheapestProviderForEachCourse = await getCheapestPriceForEachCourse(targetCurrency);
        const mostExpensiveProviderForEachCourse = await getMostExpensivePriceForEachCourse(targetCurrency);
        const categoriesData = await getCategoriesFromServer();
        const tagsData = await getTagsFromServer();
        const courseTagsData = await getCourseTagsFromServer();

        const combinedCoursesData = await CourseDataCombiner.combineCoursesWithPricesAndCategories(
          coursesData,
          cheapestProviderForEachCourse,
          mostExpensiveProviderForEachCourse,
          categoriesData,
          tagsData,
          courseTagsData
        );

        setCombinedCourses(combinedCoursesData);

        if (mostExpensiveProviderForEachCourse.data.length > 0) {
          const maxPriceValue = Math.max(...mostExpensiveProviderForEachCourse.data.map(provider => provider.price));
          setFilters(prevFilters => ({ ...prevFilters, maxPrice: maxPriceValue }));
        }
      } catch (error) {
        console.error('Error fetching courses:', error);
      }
    };

    fetchData();
  }, [targetCurrency]);

  useEffect(() => {
    const filteredCourses = filterLogic(combinedCourses, filters);
    setCourses(filteredCourses);
  }, [filters, combinedCourses]);

  const handleSortChange = (sortBy, sortOrder) => {
    setFilters(prevFilters => ({ ...prevFilters, sortBy, sortOrder }));
  };

  const handleSearchQueryChange = (query) => {
    setFilters(prevFilters => ({ ...prevFilters, searchQuery: query }));
  };

  const handlePriceChange = (min, max) => {
    setFilters(prevFilters => ({ ...prevFilters, minPrice: min, maxPrice: max }));
  };

  const handleCategoryChange = (category) => {
    setFilters(prevFilters => ({ ...prevFilters, category }));
  };

  const handleTagSearch = (tag) => {
    setFilters(prevFilters => ({ ...prevFilters, searchQuery: tag }));
  };

  return {
    combinedCourses,
    filters,
    courses,
    handleSortChange,
    handleSearchQueryChange,
    handlePriceChange,
    handleCategoryChange,
    handleTagSearch,
  };
}

export default useCoursesPageLogic;
