import "./401.jsx"
import Button from "../../../components/button/Button";

export default function Unauthorized() {
  return (
    <div className="401-container">
      <h1>Error 401: Unauthorized Access</h1>
      <p>You do not have permission to access this page.</p>
      <Button text={"Return To Homepage"} src={"/"}/>
    </div>
  );
}