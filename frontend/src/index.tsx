import React from 'react';
import ReactDOM from 'react-dom/client';
import './assets/styles/bootstrap.custom.css';
import './assets/styles/index.scss';
import App from './App';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider
  } from 'react-router-dom';
import {store} from "./store";
import {Provider} from "react-redux";  
import {HomePage} from './pages/HomePage';
import { ProductPage } from './pages/ProductPage';
import { CartPage } from './pages/CartPage';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<App />}>
      <Route index path='/' element={<HomePage/>} />
      <Route path='/product/:id' element={<ProductPage/>} />
      <Route path='/cart' element={<CartPage/>} />
    </Route>
  )
);

//wrap the app in the redux store provider
root.render(
  <React.StrictMode>
    <Provider store={store}> 
      <RouterProvider router={router}/>
    </Provider>
  </React.StrictMode>
);
