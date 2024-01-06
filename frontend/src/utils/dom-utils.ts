export const getElementById = (className: string): HTMLElement => {
  return getElementOrThrow(document.getElementById(className), className);
};

export const qs = (className: string): HTMLElement => {
  return getElementOrThrow(document.querySelector(className), className);
};

export const qsa = (className: string): NodeListOf<HTMLElement> => {
    return getElementsOrThrow(document.querySelectorAll(className), className);
};

const getElementOrThrow = (element: HTMLElement | null, className: string): HTMLElement => {
  if (element) {
    return element;
  } else {
    throw Error(`Element ".${className}" not found!`);
  }
};

const getElementsOrThrow = (elements: NodeListOf<HTMLElement> | null, className: string): NodeListOf<HTMLElement> => {
    if (elements) {
      return elements;
    } else {
      throw Error(`Elements with ".${className}" not found!`);
    }
  };

export const add = (element: Element, className: string) => {
  element.classList.add(className);
};

export const remove = (element: Element, className: string) => {
  element.classList.remove(className);
};

export const toggle = (element: Element, className: string): boolean =>
  element.classList.toggle(className);

export const doNothing = (): void => {
    /** Intentionally left blank */
};
