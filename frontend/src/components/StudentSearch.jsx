import { useState, useEffect } from "react";
import { useAuthContext } from "../context/AuthContext";
import { toast } from "react-toastify";

const StudentSearch = () => {
  const { state } = useAuthContext();
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [searchQuery, setSearchQuery] = useState({
    name: "",
    department: "",
    year: "",
  });


  // Call API whenever searchQuery changes
  useEffect(() => {
    const fetchStudents = async (query) => {
        setLoading(true);
        try {
          const { name, department, year } = query;
    
          // Prepare query parameters for the API call
          const queryParams = new URLSearchParams()
          if (name.trim()) queryParams.append("name", name.trim());
          if (department.trim()) queryParams.append("departmentName", department.trim());
          if (year.trim()) queryParams.append("year", year.trim());

          const response = await fetch(`/api/student/search?${queryParams.toString()}`, {
            method: "GET",
            headers: {
              Authorization: `Bearer ${state.user.token}`,
            },
          });

          console.log(response)
    
          if (!response.ok) {
            //   const data = await response.json();
            //   console.log(data)
              throw new Error("Failed to fetch students data");
            }
            
            const data = await response.json();
          setStudents(data); // Set the data from the API response
        } catch (err) {
            console.log(err)
          setError(err.message);
          toast.error("Failed to load students");
        } finally {
          setLoading(false);
        }
      };
    
    if (state.user && state.user.role === "STUDENT" && (searchQuery.name.trim() || searchQuery.department.trim() || searchQuery.year.trim())) {
      fetchStudents(searchQuery); // Call the API with the latest search query
    }
  }, [searchQuery, state.user]); // Effect runs when searchQuery or user state changes

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSearchQuery((prevQuery) => ({
      ...prevQuery,
      [name]: value,
    }));
  };

  if (loading) {
    return <div className="text-center mt-10">Loading students...</div>; // Show loading while fetching data
  }

  if (error) {
    return <div className="text-center text-red-500 mt-10">{error}</div>;
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="bg-white shadow-lg rounded-lg p-8 max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold mb-6 text-center">Search Students</h2>
        <div className="mb-4">
          <input
            type="text"
            name="name"
            placeholder="Search by name"
            value={searchQuery.name}
            onChange={handleInputChange}
            className="border p-2 rounded w-full mb-2"
          />
          <input
            type="text"
            name="department"
            placeholder="Search by department"
            value={searchQuery.department}
            onChange={handleInputChange}
            className="border p-2 rounded w-full mb-2"
          />
          <input
            type="text"
            name="year"
            placeholder="Search by year"
            value={searchQuery.year}
            onChange={handleInputChange}
            className="border p-2 rounded w-full mb-4"
          />
        </div>

        <h3 className="text-xl font-bold mb-4">Search Results</h3>
        {students.length === 0 ? (
          <p className="text-center">No matching students found.</p>
        ) : (
          <ul className="list-disc pl-5">
            {students.map((student) => (
              <li key={student.userId} className="mb-2">
                <span className="font-medium">{student.user.name}</span> -{" "}
                <span className="text-gray-600">{student.department.name} ({student.year})</span>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default StudentSearch;
