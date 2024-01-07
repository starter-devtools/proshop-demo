import { createSlice } from "@reduxjs/toolkit";
import { updateCart } from "../utils/cart-utils";
import { initialCart } from "../api/CartResponse";
import { ProductResponse } from "../api/ProductResponse";

const storage = localStorage.getItem("cart");
const initialState = storage ? JSON.parse(storage) : initialCart;

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addToCart: (state, action) => {
            const item = action.payload;
            const existItem = state.cartItems.find((x: ProductResponse) => x.id === item.id);
            
            if (existItem) {
                state.cartItems = state.cartItems.map((x: ProductResponse) => x.id === existItem.id ? item : x);
            } else {
                //state is immutable, so we have to copy it, and then add the new item.
                state.cartItems = [...state.cartItems, item];
            }
        
            return updateCart(state);
        },
        removeFromCart: (state, action) => {
            state.cartItems = state.cartItems.filter((x) => x.id !== action.payload);
            return updateCart(state);
        }
    }
});

export const { addToCart, removeFromCart } = cartSlice.actions;

export default cartSlice.reducer;