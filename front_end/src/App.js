import React, {useEffect} from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login/Login";
import SignUp from "./pages/SignUp/SignUp";
import WelcomeScreen from "./pages/Welcome Screen/WelcomeScreen";
import NavBar from "./pages/MainMenu/Navbar";
import useAuth from "./context/useAuth";

const ROLES = {
    'User': 2001,
    'Admin': 5150
}

function App() {

    const {setAuth} = useAuth()

    const username = localStorage.getItem("username") || "";
    const email = localStorage.getItem("email") || "";
    const role = localStorage.getItem("role") || "";
    const accessToken = localStorage.getItem("accessToken") || "";

    useEffect( () => {
        setAuth({ username , email , role , accessToken })
        console.log(localStorage.getItem("username") || "empty username")
        console.log(localStorage.getItem("email") || "empty email")
        console.log(localStorage.getItem("role") || "empty role")
        console.log(localStorage.getItem("accessToken") || "empty token")
    },[]);

      return (
          <div className="App">
              <Routes>
                <Route path="/" element={<WelcomeScreen/>} />
                  {/*<Route element={<RequireAuth allowedRoles={[ROLES.User]}/>}>
                      <Route path="/login" element={<Login/>}/>
                  </Route>*/}
                  <Route path="/login" element={<Login/>}/>
                <Route path="/signup" element={<SignUp/>} />
                <Route path="/home" element={<NavBar/>} />
              </Routes>
          </div>
      );
}

export default App;