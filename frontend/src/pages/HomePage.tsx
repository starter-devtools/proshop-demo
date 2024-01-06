import {Row, Col} from 'react-bootstrap';
import {Product} from '../components/Product';
import { useListProductsQuery } from '../slices/product-api-slice';
import { Loader } from '../components/Loader';
import { Message } from '../components/Message';

export const HomePage = () => {
  const {data: products, isLoading, error} = useListProductsQuery('');

  return (
    <>
        {isLoading 
        ? (<Loader />) : error 
        ? (<Message variant='danger'>{error?.data?.message || error?.error}</Message>) 
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