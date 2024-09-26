import { useState, useEffect } from "react";
import { useAuthContext } from "../context/AuthContext";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom"; // For redirection
import "react-toastify/dist/ReactToastify.css";

const StudentProfile = () => {
  const { state } = useAuthContext(); // To access the logged-in user
  const [profileData, setProfileData] = useState(null);
  const [academicData, setAcademicData] = useState([]); // Initialize as an empty array
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // Hook for redirection

  useEffect(() => {
    // Check if user is logged in and has the 'STUDENT' role
    if (state.user && state.user.role !== "STUDENT") {
      toast.error("Unauthorized Access");
      navigate("/"); // Redirect to homepage or a relevant page
      return;
    }

    const fetchProfileData = async () => {
      try {
        // Fetch student profile
        const response = await fetch(`/api/student/profile`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${state.user.token}`, // Include JWT token
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch profile data");
        }

        const data = await response.json();
        console.log(data)
        setProfileData(data);

        // Fetch academic data
        const academicResponse = await fetch(`/api/student/academic`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${state.user.token}`, // Include JWT token
          },
        });

        if (!academicResponse.ok) {
          throw new Error("Failed to fetch academic data");
        }

        const academicData = await academicResponse.json();
        setAcademicData(academicData);
      } catch (err) {
        setError(err.message);
        toast.error("Failed to load data");
      } finally {
        setLoading(false);
      }
    };

    if (state.user && state.user.role === "STUDENT") {
      fetchProfileData();
    }
  }, [state.user, navigate]);

  if (loading) {
    return <div className="text-center mt-10">Loading profile...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500 mt-10">{error}</div>;
  }

  if (!profileData || academicData.length === 0) {
    return <div className="text-center mt-10">No profile or academic data available.</div>;
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="bg-white shadow-lg rounded-lg p-8 max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold mb-6 text-center">Student Profile</h2>

        {/* Personal Information Section */}
        <div className="mb-8">
          <h3 className="text-xl font-bold mb-4">Personal Information</h3>
          <div className="flex items-center mb-4">
            <img
              src={profileData.photo || "/default-avatar.png"} // Use default image if no photo
              alt="Profile"
              className="w-20 h-20 rounded-full mr-4"
            />
            <div>
              <p className="text-lg font-semibold">{profileData.user.name}</p>
              <p className="text-gray-600">{profileData.user.email}</p>
              <p className="text-gray-600">{profileData.user.phone}</p>
            </div>
          </div>
        </div>

        {/* Academic Information Section */}
        <div className="mb-8">
          <h3 className="text-xl font-bold mb-4">Academic Information</h3>
          {academicData.map((record) => (
            <div key={record.id} className="mb-6 border-b pb-4">
              <h4 className="font-semibold">{record.course.title}</h4>
              {/* <p className="text-gray-600">{record.course.description}</p> */}
              <p className="text-gray-600">Marks: <span className="font-medium">{record.marks}</span></p>
              <p className="text-gray-600">Classes Attended: <span className="font-medium">{record.attendance}</span></p>
              <div className="mt-2">
                <h5 className="font-semibold">Faculty Information</h5>
                <p className="text-gray-600">Name: {record.course.faculty.user.name}</p>
                <p className="text-gray-600">Email: {record.course.faculty.user.email}</p>
                <p className="text-gray-600">Phone: {record.course.faculty.user.phone}</p>
                <p className="text-gray-600">Office Hours: {record.course.faculty.officeHours}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default StudentProfile;
