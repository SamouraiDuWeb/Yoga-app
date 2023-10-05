import { SessionInformation } from './sessionInformation.interface';
import { expect } from '@jest/globals';

describe('SessionInformation', () => {
  it('should create a session information object', () => {
    const sessionInfo: SessionInformation = {
      token: 'abc123',
      type: 'Bearer',
      id: 1,
      username: 'johndoe@test.com',
      firstName: 'John',
      lastName: 'Doe',
      admin: true
    };

    expect(sessionInfo).toBeDefined();
    expect(sessionInfo.token).toEqual('abc123');
    expect(sessionInfo.type).toEqual('Bearer');
    expect(sessionInfo.id).toEqual(1);
    expect(sessionInfo.username).toEqual('johndoe@test.com');
    expect(sessionInfo.firstName).toEqual('John');
    expect(sessionInfo.lastName).toEqual('Doe');
    expect(sessionInfo.admin).toEqual(true);
  });
});
