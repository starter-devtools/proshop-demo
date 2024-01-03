import {Row, Col} from 'react-bootstrap';
import products from '../data/products';
import {Product} from '../components/Product';

export const HomePage = () => {
  return (
    <>
        <h1>Latest Products</h1>
        <Row>
            {products.map((product) => (
              <Col sm={12} md={6} lg={4} xl={3}>
                <Product styles='' product={product}/>
              </Col>  
            ))}
        </Row>
    </>
  );
};