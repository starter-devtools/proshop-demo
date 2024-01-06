import { FC, ReactNode } from 'react'
import { Alert } from 'react-bootstrap';

interface MessageProps {
    variant: string;
    children: ReactNode;
}

export const Message: FC<MessageProps> = ({variant, children}) => {
  return <Alert variant={variant}>{children}</Alert>
};

Message.defaultProps = {
    variant: 'info'
};