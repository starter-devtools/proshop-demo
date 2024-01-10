import { apiSlice } from "./api-slice";
import { AUTH_URL, USERS_URL } from "../constants";
import { LoginResponse } from "../api/UserResponse";

/**
 * Make API requests using redux tools.
 */
const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        //Change to Mutation, since we're not fetching data
        // login: builder.mutation<string, LoginResponse>({
        login: builder.mutation({
            query: (data) => ({
                url: `${AUTH_URL}/login`,
                method: 'POST',
                body: data
            }),
        })
    })
});

//Export the GET request as a hook
export const { useLoginMutation } = usersApiSlice;