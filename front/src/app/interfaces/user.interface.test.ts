import { User } from './user.interface';
import { expect } from '@jest/globals';

describe('User', () => {
  it('creates a User object', () => {
    const user: User = {
      id: 1,
      email: 'john.doe@example.com',
      lastName: 'Doe',
      firstName: 'John',
      admin: true,
      password: 'password123',
      createdAt: new Date(),
      updatedAt: new Date()
    };

    const expectedUser = {
      id: 1,
      email: 'john.doe@example.com',
      lastName: 'Doe',
      firstName: 'John',
      admin: true,
      password: 'password123'
    };

    expect(user).toMatchObject(expectedUser);
    expect(user.createdAt).toBeInstanceOf(Date);
    expect(user.updatedAt).toBeInstanceOf(Date);
  });
});
