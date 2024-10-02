// package no.ntnu.backend.model;

// import java.util.Objects;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// // @Entity
// public class UserCourse {

//   private int user_id;
//   private int course_id;
//   private boolean completed;

//   public UserCourse() {
//   }

//   public UserCourse(int user_id, int course_id, boolean completed) {
//     this.user_id = user_id;
//     this.course_id = course_id;
//     this.completed = completed;
//   }

//   public int getCourseId() {
//     return course_id;
//   }

//   public void setCourseId(int course_id) {
//     this.course_id = course_id;
//   }

//   public int getUserId() {
//     return user_id;
//   }

//   public void setUserId(int user_id) {
//     this.user_id = user_id;
//   }

//   public boolean getCompleted() {
//     return this.completed;
//   }

//   public void setCompleted(boolean completed) {
//     this.completed = completed;
//   }

//   @Override
//     public boolean equals(Object obj) {
//         if (obj == this) return true;
//         if (obj == null || obj.getClass() != this.getClass()) return false;
//         var that = (UserCourse) obj;
//         return this.course_id == that.course_id &&
//                 Objects.equals(this.user_id, that.user_id) &&
//                 Objects.equals(this.completed, that.completed);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(user_id, course_id, completed);
//     }

//     @Override
//     public String toString() {
//         return "User[" +
//                 "user_id=" + user_id + ", " +
//                 "course_id=" + course_id + ", " + 
//                 "completed=" + completed + "]";
//     }
// }