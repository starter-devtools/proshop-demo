import type { Config } from '@jest/types';

const jestConfig = async (): Promise<Config.InitialOptions> => {
  return {
    testEnvironment: "jsdom",
    preset: 'ts-jest',
    bail: true,
    verbose: true,
    roots: ['<rootDir>'],
    setupFilesAfterEnv: ['<rootDir>/src/jest-setup.ts'],
    testRegex: "(/__tests__/.*|(\\.|/)(test|spec))\\.(ts|tsx)$",
    moduleNameMapper : {
      '^.+\\.svg$': 'svg-jest',
      '\\.(gif|ttf|eot|svg|png)$': '<rootDir>/src/tests/__mocks__/fileMock.ts',
      "^.+\\.(css|less|scss)$": "babel-jest"
    },
    transform: {
      '^.+\\.tsx?$': 'ts-jest',
    },
    moduleFileExtensions: ['js', 'json', 'node', 'ts', 'tsx'],
    collectCoverageFrom: [
      "!<rootDir>/**/*.d.ts",
      "<rootDir>/**/*.(js|ts|tsx)"
  ],
  coveragePathIgnorePatterns: [
      "/.eslintrc.js",
      "/jest.config.ts",
      "/vite.config.ts",
      "/node_modules/",
      "/templates",
      "/var/",
  ],
  coverageDirectory: "<rootDir>/var/coverage"
  };
};

export default jestConfig;