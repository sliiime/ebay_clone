import React from "react";
import { BrowserRouter, Routes, Route} from "react-router-dom";
import Login from "./pages/Login/Login";
import SignUp from "./pages/SignUp/SignUp";
import WelcomeScreen from "./pages/1. Welcome Screen/WelcomeScreen";

function App() {
  return (
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<WelcomeScreen/>} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<SignUp/>} />
          </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;