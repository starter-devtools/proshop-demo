import { configureStore } from "@reduxjs/toolkit";
import { apiSlice } from "./slices/api-slice";
import cartSliceReducer from './slices/cart-slice';

/**
 * The redux global storage
 */
export const store = configureStore({
    reducer: {
        // Add the generated reducer as a specific top-level slice
        [apiSlice.reducerPath]: apiSlice.reducer,
        cart: cartSliceReducer
    },
    // Adding the api middleware enables caching, invalidation, polling,
    // and other useful features of `rtk-query`.
    middleware: (defaultMiddleWare) => defaultMiddleWare().concat(apiSlice.middleware),
    devTools: true
});