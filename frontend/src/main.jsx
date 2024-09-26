import { createRoot } from 'react-dom/client'
import {  ToastContainer } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
import App from './App.jsx'
import './index.css'
import { AuthContextProvider } from './context/AuthContext.jsx';

createRoot(document.getElementById('root')).render(
  <AuthContextProvider>
    <App />
    <ToastContainer />
    </AuthContextProvider>,
)
