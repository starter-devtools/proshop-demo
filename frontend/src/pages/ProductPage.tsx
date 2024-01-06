import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { Row, Col, Image, ListGroup, Card, Button, ListGroupItem } from "react-bootstrap";
import {Rating} from "../components/Rating";
import { useGetProductDetailsQuery } from '../slices/product-api-slice';
import { Loader } from "../components/Loader";
import { Message } from "../components/Message";

export const ProductPage = () => {
  const { id: productId } = useParams();
  const {data: product, isLoading, error} = useGetProductDetailsQuery(productId!);

  return <>
    <Link className="btn btn-light my-3" to='/'>Go Back</Link>
    { product && 
    <Row>
        <Col md={5}>
            <Image src={product.image} alt={product.name} fluid/>
        </Col>

        {isLoading 
        ? (<Loader />) : error 
        ? (<Message variant="danger">{error?.data?.message || error?.error}</Message>)
        : 
        ( 
        <>
        <Col md={4}>
            <ListGroup variant="flush">
                <ListGroupItem>
                    <h3>{product.name}</h3>
                </ListGroupItem>
                <ListGroupItem>
                    <Rating value={product.rating} text={`${product.numReviews} reviews`}/>
                </ListGroupItem>
                <ListGroupItem>{product.description}</ListGroupItem>
            </ListGroup>
        </Col>

        <Col md={3}>
            <Card>
                <ListGroup variant="flush">
                    <ListGroupItem>
                        <Row>
                            <Col>Price:</Col>
                            <Col>
                                <strong>${product.price}</strong>
                            </Col>
                        </Row>
                    </ListGroupItem>
                    <ListGroupItem>
                        <Row>
                            <Col>Status:</Col>
                            <Col>
                                <strong>{product.countInStock > 0 ? 'In Stock' : 'Out of Stock'}</strong>
                            </Col>
                        </Row>
                    </ListGroupItem>
                    <ListGroupItem>
                        <Button className="btn-block" type="button" disabled={product.countInStock === 0}>
                            Add to Cart
                        </Button>
                    </ListGroupItem>
                </ListGroup>
            </Card>
        </Col>
        </>
        )}
    </Row>}
  </>;
};
