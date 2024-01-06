import { useParams } from "react-router-dom";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { Row, Col, Image, ListGroup, Card, Button, ListGroupItem, FormControl } from "react-bootstrap";
import {Rating} from "../components/Rating";
import { useGetProductDetailsQuery } from '../slices/product-api-slice';
import { Loader } from "../components/Loader";
import { Message } from "../components/Message";
import { useState } from "react";
import { addToCart } from "../slices/cart-slice";

export const ProductPage = () => {
  const { id: productId } = useParams();
  const {data: product, isLoading, error} = useGetProductDetailsQuery(productId!);
  const [quantity, setQuantity] = useState(1);

  //Send data to redux store
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const addToCartHandler = () => {
    //Add product to cart, increase quantity, and update state
    dispatch(addToCart({...product, quantity}));
    // navigate("/cart");
  };

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
                    {product.countInStock > 0 && (
                        <ListGroupItem>
                            <Row>
                                <Col>Quantity</Col>
                                <Col>
                                    <FormControl
                                        as='select'
                                        value={quantity}
                                        onChange={(e) => setQuantity(+e.target.value)}
                                    >
                                        {/* [...options].length === countInStock */}
                                        {[...Array(product.countInStock).keys()].map((x) => (
                                            <option key={x + 1} value={x + 1}>{x + 1}</option>
                                        ))}
                                    </FormControl>
                                </Col>
                            </Row>
                        </ListGroupItem>
                    )}
                    <ListGroupItem>
                        <Button 
                            className="btn-block" 
                            type="button" 
                            disabled={product.countInStock === 0}
                            onClick={addToCartHandler}
                        >
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
