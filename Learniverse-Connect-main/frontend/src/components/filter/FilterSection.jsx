import React from 'react';
import SearchBar from '../../components/filter/searchBar/SearchBar';
import PriceRangeFilter from '../../components/filter/pricefilter/PriceFilter';
import SortByFilter from './sortbyfilter/SortByFilter';
import CategoryFilter from './categoriesFilter/CategoryFilter';
import AutocompleteBox from '../../components/autocompleteBox/AutoCompleteBox';
import './filterSection.css';

export default function FilterSection({ onSearchQueryChange, onPriceChange, onSortChange, onCategoryChange, courses, filters, maxPrice }) {
  return (
    <section className='filter-section-container'>
      <div className='search-row'>
        <SearchBar setSearchQuery={onSearchQueryChange} />
        <AutocompleteBox courses={courses} filters={filters} />
      </div>
      <div className="filter-row" role="group" aria-labelledby="filterHeading">
        <div style={{ flex: 1 }}> {/* Add this div */}
          <PriceRangeFilter className="price-container"onPriceChange={onPriceChange} maxPrice={maxPrice} />
        </div>
        <SortByFilter onSortChange={onSortChange} />
        <CategoryFilter onCategoryChange={onCategoryChange} />
      </div>
    </section>
  );
}
