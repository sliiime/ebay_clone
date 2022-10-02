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
import AddItem from "./pages/AddItem/AddItem";
import EditItem from "./pages/EditItem/EditItem";
import Search from "./pages/Search/Search";
import Bid from "./pages/Bid/Bid";
import ChatPreview from "./pages/Chat/ChatPreview";
import Chat from "./pages/Chat/Chat";
import Recommended from "./pages/Search/Recommended";

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
                      <Route path="search" element={<Search />} />

                      {/* we want to protect these routes */}
                      <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>
                          <Route path="admin" element={<Admin />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="myitems" element={<MyItems />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="myitems/addItem" element={<AddItem />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="myitems/editItem/:id" element={<EditItem />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="recommended" element={<Recommended />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="bid/:id" element={<Bid />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="chat" element={<ChatPreview />} />
                      </Route>

                      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
                          <Route path="chat/:talkerId/:talkerName" element={<Chat />} />
                      </Route>

                      {/* catch all */}
                      <Route path="*" element={<Missing />} />
                  </Route>
              </Routes>
          </div>
      );
}

export default App;