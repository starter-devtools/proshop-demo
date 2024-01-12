import { createApi, fetchBaseQuery  } from "@reduxjs/toolkit/query/react";
import type {RootState} from "../store";

import { BASE_URL } from "../constants";

const baseQuery = fetchBaseQuery({
    baseUrl: BASE_URL,
    //Add headers to each API request.
    prepareHeaders: (headers, {getState}) => {
        const token = (getState() as RootState).auth?.userInfo?.accessToken?.data;
        if (token) {
            //TODO: Add this as an http-only cookie, and not an auth header?
            //Add JWT to each request.
            headers.set('authorization', `Bearer ${token}`);
        }
        return headers;
    }
});

/**
 * Stores server API requests in redux as pieces of state (slices).
 */
export const apiSlice = createApi({
    baseQuery,
    tagTypes: ['Product', 'Order', 'User'],
    endpoints: (builder) => ({})
});