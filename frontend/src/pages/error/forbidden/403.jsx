import "./403.jsx"
import Button from "../../../components/button/Button";

export default function Unauthorized() {
  return (
    <div className="403-container">
      <h1>Error 403: Forbidden Access</h1>
      <p>You do not have permission to access this page.</p>
      <Button text={"Return To Homepage"} src={"/"}/>
      <br/><p>Or try to:</p>
      <Button text={"Login"} src={"/login"}/>
    </div>
  );
}