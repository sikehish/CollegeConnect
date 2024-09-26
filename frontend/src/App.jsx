import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import { AuthContextProvider, useAuthContext } from "./context/AuthContext";
import StudentProfile from "./pages/StudentProfile";
import Navbar from "./components/Navbar";
import StudentSearch from "./components/StudentSearch";

function App() {
  const {state} = useAuthContext()
  return (
    <Router>
    <Navbar />
    <Routes>
    <Route path="/" element={<Landing />} />
        <Route path="/login" element={state.user ? <Navigate to="/student/search" /> : <Login />} />

        <Route 
          path="/student/profile" 
          element={state.user && state.user.role === "STUDENT" ? <StudentProfile /> : <Navigate to="/" />} 
        />
        <Route 
          path="/student/search" 
          element={state.user && state.user.role === "STUDENT" ? <StudentSearch /> : <Navigate to="/" />} 
        />
      </Routes>
    </Router>
  );
}

export default App;
