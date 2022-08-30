import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login/Login";
import SignUp from "./pages/SignUp/SignUp";
import Home from "./pages/MainMenu/Home";
import Admin from "./pages/Admin/Admin";
import RequireAuth from "./context/RequireAuth";
import Unauthorized from "./pages/Unauthorized/Unauthorized";
import Layout from "./components/Layout";
import Missing from "./pages/Missing/Missing";
import MyItems from "./pages/UsersItems/MyItems";

const ROLES = {
    'User': 2001,
    'Admin': 5150
}

function App() {
      return (
          <div className="App">
              <Routes>
                  <Route path="/" element={<Layout />}>
                      {/* public routes */}
                      <Route path="login" element={<Login />} />
                      <Route path="signup" element={<SignUp />} />
                      <Route path="unauthorized" element={<Unauthorized />} />
                      <Route path="" element={<Home />} />

                      {/* we want to protect these routes */}
                      <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>
                          <Route path="admin" element={<Admin />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="myitems" element={<MyItems />} />
                      </Route>



                      {/* catch all */}
                      <Route path="*" element={<Missing />} />
                  </Route>
              </Routes>
          </div>
      );
}

export default App;

/*

<Route path="" element={<Home />} />
                  <Route path="login" element={<Login />} />
                  <Route path="signup" element={<SignUp />} />
                  <Route path="unauthorized" element={<Unauthorized />} />
                  <Route path="admin" element={<Admin />} />
                  <Route path="*" element={<Missing />} />

 */