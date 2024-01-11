import { apiSlice } from "./api-slice";
import { AUTH_URL, USERS_URL } from "../constants";
import { LoginResponse } from "../api/UserResponse";
import { FetchBaseQueryMeta } from "@reduxjs/toolkit/query";

/**
 * Make API requests using redux tools.
 */
const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        //Change to Mutation, since we're not fetching data
        // login: builder.mutation<string, LoginResponse>({
        login: builder.mutation<string, LoginResponse>({
            query: (data) => ({
                url: `${AUTH_URL}/login`,
                method: 'POST',
                body: data
            }),
            //Converts response from body to header!
            transformResponse: async (_, meta: FetchBaseQueryMeta) => {
                const header = meta?.response?.headers?.get('authorization')
                if (header) {
                    const jwt = header?.split("Bearer")[1].trim()
                    return { data: jwt };
                } else {
                    throw Error("Login did not generate token!");
                }
            }
        })
    })
});

//Export the GET request as a hook
export const { useLoginMutation } = usersApiSlice;