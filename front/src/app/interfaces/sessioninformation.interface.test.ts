import { SessionInformation } from './sessionInformation.interface';
import { expect } from '@jest/globals';

describe('SessionInformation', () => {
  it('Create a session information object', () => {
    const sessionInfo: SessionInformation = {
      token: 'abc123',
      type: 'Bearer',
      id: 1,
      username: 'johndoe@test.com',
      firstName: 'John',
      lastName: 'Doe',
      admin: true
    };

    const expectedSessionInfo: SessionInformation = {
      token: 'abc123',
      type: 'Bearer',
      id: 1,
      username: 'johndoe@test.com',
      firstName: 'John',
      lastName: 'Doe',
      admin: true
    };

    expect(sessionInfo).toEqual(expectedSessionInfo);
  });
});
