import { apiSlice } from "./api-slice";
import { PRODUCTS_URL } from "../constants";
import { ProductResponse } from "../api/ProductResponse";

/**
 * Make API requests using redux tools.
 */
const productsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        listProducts: builder.query<ProductResponse[], string>({
            query: () => ({
                url: PRODUCTS_URL
            }),
            keepUnusedDataFor: 5 // keep data cached until the component changes.
        }),
        getProductDetails: builder.query<ProductResponse, string>({
            query: (productId) => ({
                url: `${PRODUCTS_URL}/${productId}`,
                //TODO: add jwt here as request header...
                headers: {

                }
            }),
            keepUnusedDataFor: 5
        }),
    })
});

//Export the GET request as a hook
export const { useListProductsQuery, useGetProductDetailsQuery } = productsApiSlice;