import { createSlice } from "@reduxjs/toolkit";

const storage = localStorage.getItem("cart");
const initialState = storage ? JSON.parse(storage) : {cartItems: []};

const addDecimals = (num: number) => (Math.round(num * 100) / 100).toFixed(2);

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

            //Calculate items price
            state.itemsPrice = addDecimals(state.cartItems.reduce((acc, item) => acc + item.price * item.qty, 0));

            //Calc shipping price
            state.shippingPrice = addDecimals(state.itemsPrice > 100 ? 0 : 10);

            //Calc tax price
            state.taxPrice = addDecimals(Number((0.15 * state.itemsPrice).toFixed(2)));

            //calc total price
            state.totalPrice = (
                Number(state.itemsPrice) +
                Number(state.shippingPrice) +
                Number(state.taxPrice) 
            ).toFixed(2);

            localStorage.setItem('cart', JSON.stringify(state));
        }
    }
});

export const { addToCart } = cartSlice.actions;

export default cartSlice.reducer;