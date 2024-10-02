//Class generated with the help of ChatGPT

class CourseDataCombiner {
  static async combineCoursesWithPricesAndCategories(courses, cheapestProvider, mostExpensiveProvider, category, tags, courseTags) {
    try {
      const tagIdToNameMap = tags.data.reduce((map, tag) => {
        map[tag.id] = tag.tag;
        return map;
      }, {});

      const courseIdToTagsMap = courseTags.data.reduce((map, courseTag) => {
        if (!map[courseTag.courseId]) {
          map[courseTag.courseId] = [];
        }
        map[courseTag.courseId].push(tagIdToNameMap[courseTag.tagId]);
        return map;
      }, {});
      const coursesWithData = courses.data.map((course) => {
        const tags = courseIdToTagsMap[course.id] || [];

        const cheapestCourseData = cheapestProvider.data.find(provider => provider.courseId === course.id);
        
        const mostExpensiveCourseData = mostExpensiveProvider.data.find(provider => provider.courseId === course.id);

        const courseWithCategoryAndTags = {
          ...course,
          cheapestPrice: cheapestCourseData ? cheapestCourseData.price : null,
          cheapestCurrency: cheapestCourseData ? cheapestCourseData.currency : null,
          mostExpensivePrice: mostExpensiveCourseData ? mostExpensiveCourseData.price : null,
          tags: tags
        };
        console.log(courseWithCategoryAndTags);
        return courseWithCategoryAndTags;
      });

      
      return coursesWithData;
    } catch (error) {
      console.error('Error combining courses with prices, categories, and tags:', error);
      return [];
    }
  }
}

export default CourseDataCombiner;
