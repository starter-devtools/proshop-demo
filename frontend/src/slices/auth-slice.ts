import { createSlice } from "@reduxjs/toolkit";
import { AuthResponse } from "../api/UserResponse";

const local = localStorage.getItem('userInfo');
const localData: AuthResponse = local ? JSON.parse(local) : null;
const initialState = {
    userInfo: localData ? localData : null,
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setCredentials: (state, action: {payload: AuthResponse}) => {
            state.userInfo = action.payload;
            localStorage.setItem('userInfo', JSON.stringify(action.payload))
        },
        logout: (state) => {
            state.userInfo = null; //reset the state
            localStorage.removeItem('userInfo') //clear local storage
        }
    }
});

export const { setCredentials, logout } = authSlice.actions;
export default authSlice.reducer;