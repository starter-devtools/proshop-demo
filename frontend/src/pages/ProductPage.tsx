import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { Row, Col, Image, ListGroup, Card, Button, ListGroupItem } from "react-bootstrap";
import {Rating} from "../components/Rating";
import products from "../data/products";
import axios from "axios";
import { Product } from "../common/PropTypes";

export const ProductPage = () => {
//   const [product, setProduct] = useState<Product>({
//     _id: '',
//     name: '',
//     image: '',
//     description: '',
//     brand: '',
//     category: '',
//     price: 0,
//     countInStock: 0,
//     rating: 0,
//     numReviews: 0
//   });

  const { id: productId } = useParams();
  /* eslint-disable */
  const product = products.find((p: Product) => p._id === productId)!;

//   useEffect(() => {
//     const fetchProduct = async () => {
//       const { data } = await axios.get(`/api/products/${productId}`);
//       setProduct(data);
//     };
//     fetchProduct();
//   }, [productId]); //re-render component if different ID

  return <>
    <Link className="btn btn-light my-3" to='/'>Go Back</Link>
    <Row>
        <Col md={5}>
            <Image src={product.image} alt={product?.name} fluid/>
        </Col>
        
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
    </Row>
  </>;
};
