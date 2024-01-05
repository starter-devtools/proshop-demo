import {Row, Col} from 'react-bootstrap';
import {Product} from '../components/Product';
import { useListProductsQuery } from '../slices/product-api-slice';

export const HomePage = () => {
  const {data: products, isLoading, error} = useListProductsQuery('');

  return (
    <>
        {isLoading 
        ? (<h2>Loading...</h2>) : error 
        ? (<div>{error?.data?.message || error?.error}</div>) 
        : (
          <>
          <h1>Latest Products</h1>
          <Row>
            {products && [...products].map((product) => (
                <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                  <Product styles='' product={product}/>
                </Col>  
              ))}
          </Row>
          </>
        )}

    </>
  );
};