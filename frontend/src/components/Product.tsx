import { FunctionComponent } from 'react'
import { Card, CardBody, CardImg, CardTitle, CardText } from 'react-bootstrap';
import { ProductProps } from '../common/PropTypes';
import { Link } from 'react-router-dom';

export const Product: FunctionComponent<ProductProps> = (props) => {
  const {id, image, name, price} = props.product;
  return (
    <Card className='my-3 p-3 rounded'>
        <Link to={`/product/${id}`}>
            <CardImg src={image} variant='top'/>
        </Link>
        <CardBody>
            <Link to={`/product/${id}`}>
                <CardTitle as='div'>
                    <strong>{name}</strong>
                </CardTitle>
            </Link>
            <CardText as='h3'>${price}</CardText>
        </CardBody>
    </Card>
  )
};