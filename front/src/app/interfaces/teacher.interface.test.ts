import { Teacher } from './teacher.interface';
import { expect } from '@jest/globals';

describe('Teacher', () => {
  it('Create a Teacher object', () => {
    const teacher: Teacher = {
      id: 1,
      lastName: 'Doe',
      firstName: 'John',
      createdAt: new Date(),
      updatedAt: new Date()
    };

    const expectedTeacher = {
      id: 1,
      lastName: 'Doe',
      firstName: 'John'
    };

    expect(teacher).toMatchObject(expectedTeacher);
    expect(teacher.createdAt).toBeInstanceOf(Date);
    expect(teacher.updatedAt).toBeInstanceOf(Date);
  });
});
