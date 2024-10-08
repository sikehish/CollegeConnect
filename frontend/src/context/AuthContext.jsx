import { createContext, useReducer, useContext } from "react";

const AuthContext = createContext();

export const useAuthContext = () => {
  const val = useContext(AuthContext);
  //Need to throw error if value is being acesed outside the context defined
  return val;
};

const reducer = (state, action) => {
  if (action.type === "LOGIN") {
    return { user: action.payload };
  } else if (action.type === "LOGOUT") {
    return { user: null };
  } else return state;
};

export const AuthContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, {
    user: JSON.parse(localStorage.getItem("user")) || null,
  });

  return (
    <AuthContext.Provider value={{ dispatch, state }}>
      {children}
    </AuthContext.Provider>
  );
};