import {useState, useEffect} from "react";
import {Row, Col} from 'react-bootstrap';
import {Product} from '../components/Product';
import axios from "axios";
import { ProductResponse } from "../api/ProductResponse";

export const HomePage = () => {
  const [products, setProducts] = useState<ProductResponse[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      const { data } = await axios.get('/api/products');
      setProducts(data);
    };
    fetchProducts();
  }, []); //re-render component if different ID

  return (
    <>
        <h1>Latest Products</h1>
        <Row>
          {[...products].map((product) => (
              <Col key={product._id} sm={12} md={6} lg={4} xl={3}>
                <Product styles='' product={product}/>
              </Col>  
            ))}
        </Row>
    </>
  );
};