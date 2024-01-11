import { configureStore } from "@reduxjs/toolkit";
import { apiSlice } from "./slices/api-slice";
import cartSliceReducer from './slices/cart-slice';
import authSliceReducer from './slices/auth-slice';

/**
 * The redux global storage
 */
export const store = configureStore({
    reducer: {
        // Add the generated reducer as a specific top-level slice
        [apiSlice.reducerPath]: apiSlice.reducer,
        cart: cartSliceReducer,
        auth: authSliceReducer
    },
    // Adding the api middleware enables caching, invalidation, polling,
    // and other useful features of `rtk-query`.
    middleware: (defaultMiddleWare) => defaultMiddleWare().concat(apiSlice.middleware),
    devTools: true
});

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>

// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
// export type AppDispatch = typeof store.dispatch