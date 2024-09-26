import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import { AuthContextProvider } from "./context/AuthContext";
import StudentProfile from "./pages/StudentProfile";

function App() {
  return (
    <AuthContextProvider>
    <Router>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/login" element={<Login />} />
        <Route path="/student" element={<StudentProfile />} />
      </Routes>
    </Router>
    </AuthContextProvider>
  );
}

export default App;
