
const Landing = () => {
  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-500 to-purple-600 flex flex-col justify-center items-center">
      <h1 className="text-5xl font-bold text-white mb-4">
        Welcome to CollegeConnect
      </h1>
      <p className="text-xl text-white mb-8">
        Your one-stop solution for managing academic and personal profiles.
      </p>
      <a
        href="/login"
        className="bg-white text-blue-500 font-semibold py-3 px-6 rounded-md hover:bg-blue-100 transition duration-300"
      >
        Get Started
      </a>
    </div>
  );
};

export default Landing;
