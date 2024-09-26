import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext"; // Adjust the path based on your structure
import { toast } from "react-toastify";

const Navbar = () => {
  const { state, dispatch } = useAuthContext();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch({ type: "LOGOUT" }); // Assuming you have a logout action
    localStorage.removeItem("user");
    toast.success("Logged out successfully");
    navigate("/"); // Redirect to homepage or login page
  };

  return (
    <nav className="bg-gray-800 p-4">
      <div className="container mx-auto flex justify-between items-center">
        <Link to="/" className="text-white text-xl font-bold">
          CollegeConnect
        </Link>
        <div className="flex items-center">
          {state.user ? (
            <>
              {/* Conditional navigation links for STUDENT role */}
              {state.user.role === "STUDENT" && (
                  <>
                  <Link to="/student/profile" className="text-white px-4 py-2 rounded hover:bg-gray-700">
                    Profile
                  </Link>
                  <Link to="/student/search" className="text-white px-4 py-2 rounded hover:bg-gray-700">
                    Search
                  </Link>
                </>
              )}
              <div className="flex justify-center items-center ml-7">
  <span className="text-white mr-4">Hey, {state.user.username}</span>
  <button
    onClick={handleLogout}
    className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
  >
    Logout
  </button>
</div>

            </>
          ) : (
            <Link to="/login" className="text-white px-4 py-2 rounded hover:bg-gray-700">
              Login
            </Link>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
