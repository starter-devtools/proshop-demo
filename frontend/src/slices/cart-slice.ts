import { createSlice } from "@reduxjs/toolkit";
import { addDecimals, updateCart } from "../utils/cart-utils";

const storage = localStorage.getItem("cart");
const initialState = storage ? JSON.parse(storage) : { cartItems: []};

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addToCart: (state, action) => {
            const item = action.payload;
            const existItem = state.cartItems.find((x) => x._id === item._id);
            
            if (existItem) {
                state.cartItems = state.cartItems.map((x) => x._id === existItem._id ? item : x);
            } else {
                //state is immutable, so we have to copy it, and then add the new item.
                state.cartItems = [...state.cartItems, item];
            }
        
            return updateCart(state);
        }
    }
});

export const { addToCart } = cartSlice.actions;

export default cartSlice.reducer;