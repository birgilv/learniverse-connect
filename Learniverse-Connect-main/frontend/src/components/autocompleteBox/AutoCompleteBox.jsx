import React from 'react';
import { Link } from 'react-router-dom';
import { filterLogic } from '../../pages/courses/FilterLogic';

function AutocompleteBox({ courses, filters }) {
  const filteredAndSortedCourses = filterLogic(courses, filters);

  const highlightText = (text, query) => {
    if (!query.trim() || !text.toLowerCase().includes(query.toLowerCase())) {
      return text;
    }

    const parts = text.split(new RegExp(`(${query})`, 'gi'));
    
    return parts.map((part, index) => (
      part.toLowerCase() === query.toLowerCase() ? <strong key={index}>{part}</strong> : part
    ));
  };

  if (!filters.searchQuery.trim() || filteredAndSortedCourses.length === 0) {
    return null; 
  }

  const maxItemsToShow = 5;

 return (
    <section className="autocomplete-scrollable">
        {filteredAndSortedCourses.slice(0, maxItemsToShow).map((course) => (
          <Link to={`/course/${course.id}`} key={course.id} className="autocomplete-item-link">
            <article className="autocomplete-item">
              {highlightText(course.title, filters.searchQuery)}
            </article>
          </Link>
        ))}
    </section>
  );
}
export default AutocompleteBox;
