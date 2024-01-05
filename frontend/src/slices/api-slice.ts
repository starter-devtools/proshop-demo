import { createApi, fetchBaseQuery  } from "@reduxjs/toolkit/query/react";
import { BASE_URL } from "../constants";


const baseQuery = fetchBaseQuery({baseUrl: BASE_URL});

/**
 * Stores server API requests in redux as pieces of state (slices).
 * 
 */
export const apiSlice = createApi({
    baseQuery,
    tagTypes: ['Product', 'Order', 'User'],
    endpoints: (builder) => ({})
});