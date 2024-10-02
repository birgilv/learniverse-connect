import "./404.css"
import Button from "../../../components/button/Button";

export default function NotFound() {
  return (
    <div className="404-container">
      <h1>Error 404: Page Not Found</h1>
      <p>This page does not exist</p>
      <Button text={"Return To Homepage"} src={"/"}/>
    </div>
  );
}